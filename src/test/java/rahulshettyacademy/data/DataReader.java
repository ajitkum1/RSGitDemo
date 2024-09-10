package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		// Read json to String
		String jsonContent = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + " //src//test//java//rahulshettyacademy//data//PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		// Convert String to HashMap (need Jackson Databind for conversion)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		// data -- it store two arguments {map,map1}
		return data;
	}

}
