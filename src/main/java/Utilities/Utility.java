package Utilities;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import io.cucumber.datatable.DataTable;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.json.JSONObject;
import org.apache.commons.io.IOUtils;

public class Utility {
    LoggerLoad log = new LoggerLoad();
    String nestedJson="";
    public static String returnData(DataTable dataTable, String key) {
        //get the number of rows in the data table
        String value="";
        int rows = dataTable.asLists().size();
        //get the number of columns in the data table
        int columns = dataTable.asLists().get(0).size();
        //create a 2D array to store the data table data
        String[][] data = new String[rows][columns];
        //loop through the data table and store the data in the 2D array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
             //return based on the value header
                if (dataTable.asLists().get(0).get(j).equalsIgnoreCase(key))
                {
                    data[i][j] = dataTable.asLists().get(i).get(j);
                    value = data[i][j];
                }
        }
        //return the string value
        return value;
    }

    //method to read json file and return json object
    public JSONObject readJsonFile(String fileName) {
        InputStream inputJson;
        JSONObject requestJson = null;

        try {
            //read json file
            String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\testData\\"+fileName;
            log.info("File path "+filePath);
            inputJson = new FileInputStream(filePath);
            log.info("Input json "+inputJson.toString());
            String jsonInput = IOUtils.toString(inputJson, StandardCharsets.UTF_8);
            requestJson = new JSONObject(jsonInput);
        } catch (Exception e) {
            log.info("Exception "+e.getMessage());
        }

         return requestJson;
    }

    //method to update json field values
    public String updateJson(JSONObject json, String key, String value) {
        //flatten the json with json flatten
        Map<String,Object> flattenedJson = JsonFlattener.flattenAsMap(json.toString());
        flattenedJson.put(key, value);
        nestedJson = JsonUnflattener.unflatten(flattenedJson.toString());
        //return as string
        return nestedJson;
    }
}
