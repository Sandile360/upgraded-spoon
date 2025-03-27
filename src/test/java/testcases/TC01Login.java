package testcases;

import driverFactory.DriverFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import util.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class TC01Login extends DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(TC01Login.class);

//    @Test
    public void positiveLogin() throws IOException {
//        Access test data from properties
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(Utility.fetchTestData("email"));
        loginPage.enterPassword(Utility.fetchTestData("password"));
        loginPage.confirmLogin();
    }


    @Test(dataProvider = "Excel")
    public void dataProviderLogin(String email,String password) throws IOException {
//        Access test data from data providers
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(email);
        loginPage.enterPassword(password);
        loginPage.confirmLogin();
    }

    @DataProvider(name = "Excel")
        public Object[][] testDataGenerator() throws Exception {
            String filePath = System.getProperty("excel.file.path", "/home/sandile/Desktop/github/data-driven-framework/src/test/java/testData/TestData.xlsx");

            try (FileInputStream file = new FileInputStream(filePath);
                 XSSFWorkbook workbook = new XSSFWorkbook(file)) {

                XSSFSheet loginSheet = workbook.getSheet("login");

                if (loginSheet == null) {
                    throw new Exception("The 'login' sheet is not present in the Excel file.");
                }

                int numData = loginSheet.getPhysicalNumberOfRows();
                if (numData == 0) {
                    throw new Exception("No data found in the login sheet of the Excel file.");
                }

                Object[][] testData = new Object[numData][2];

                for (int i = 0; i < numData; i++) {
                    XSSFRow row = loginSheet.getRow(i);

                    if (row != null) {
                        XSSFCell email = row.getCell(0);
                        XSSFCell password = row.getCell(1);

                        // Ensure the cells are not null and fetch their values
                        if (email != null && password != null) {
                            testData[i][0] = getStringValue(email);
                            testData[i][1] = getStringValue(password);
                        } else {
                            logger.warn("Row {} has empty email or password fields.", i);
                        }
                    }
                }

                logger.info("Successfully read {} rows of data from the Excel file.", numData);
                return testData;

            } catch (FileNotFoundException e) {
                logger.error("Excel file not found: {}", filePath);
                throw new Exception("Excel file not found", e);
            } catch (IOException e) {
                logger.error("Error reading the Excel file: {}", filePath);
                throw new Exception("Error reading the Excel file", e);
            } catch (Exception e) {
                logger.error("Error occurred while processing the Excel file: {}", filePath);
                throw new Exception("General error occurred while processing the Excel file", e);
            }
        }

        // Utility method to handle different cell types
        private String getStringValue(XSSFCell cell) {
            return switch (cell.getCellType()) {
                case STRING -> cell.getStringCellValue();
                case NUMERIC -> String.valueOf(cell.getNumericCellValue());
                case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
                default -> "";
            };
        }
    }

