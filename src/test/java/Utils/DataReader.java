package Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public static HashMap<String, String> getCreateRepoData() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/GitHubAPIData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("createRepoData");
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        List<String> headerList = new ArrayList<>();
        for (int i = 0; i < colCount; i++) {
            Cell cell = sheet.getRow(0).getCell(i);
            headerList.add(cell.getStringCellValue());
        }
        HashMap<String, String> data = new HashMap<>();
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                Cell cell = sheet.getRow(i).getCell(j);
                String cellValue = "";
                if (cell != null) {
                    CellType cellType = cell.getCellType();
                    if (cellType == CellType.STRING) {
                        cellValue = cell.getStringCellValue();
                    } else if (cellType == CellType.BOOLEAN) {
                        cellValue = Boolean.toString(cell.getBooleanCellValue());
                    } else if (cellType == CellType.NUMERIC) {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                }
                data.put(headerList.get(j), cellValue);
            }
        }
        workbook.close();
        fis.close();
        return data;
    }
}
