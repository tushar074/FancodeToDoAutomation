package com.fancode.common;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.fancode.request.CommonRequestSpec;
import io.restassured.response.Response;

public class TodoUtils {

	public static JsonArray getUsers() {
		Response response = CommonRequestSpec.getRequestSpec().when().get("/users").then()
				.spec(CommonResponseSpec.getResponseSpec()).extract().response();
		return JsonParser.parseString(response.asString()).getAsJsonArray();
	}

	public static List<Integer> filterFanCodeUsers(JsonArray users) {
		List<Integer> fanCodeUserIds = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			JsonObject user = users.get(i).getAsJsonObject();
			JsonObject address = user.getAsJsonObject("address");
			JsonObject geo = address.getAsJsonObject("geo");

			double lat = Double.parseDouble(geo.get("lat").getAsString());
			double lng = Double.parseDouble(geo.get("lng").getAsString());

			if (lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100) {
				fanCodeUserIds.add(user.get("id").getAsInt());
			}
		}
		return fanCodeUserIds;
	}

	public static double getTaskCompletionPercentage(int userId) {
		Response todosResponse = CommonRequestSpec.getRequestSpec().when().get("/todos?userId=" + userId).then()
				.spec(CommonResponseSpec.getResponseSpec()).extract().response();
	
		JsonArray todos = JsonParser.parseString(todosResponse.asString()).getAsJsonArray();
	
		int totalTasks = todos.size();
		int completedTasks = 0;
	
		for (int j = 0; j < todos.size(); j++) {
			JsonObject todo = todos.get(j).getAsJsonObject();
			if (todo.get("completed").getAsBoolean()) {
				completedTasks++;
			}
		}
	
		// Debugging: Print total tasks and completed tasks
		System.out.println("Total tasks for User " + userId + ": " + totalTasks);
		System.out.println("Completed tasks for User " + userId + ": " + completedTasks);
	
		// Ensure proper percentage calculation
		double completionPercentage = totalTasks == 0 ? 0 : (completedTasks * 100.0) / totalTasks; 
		System.out.println("Completion Percentage for User " + userId + ": " + completionPercentage);
		
		return completionPercentage; // Corrected line
	}
	
	
	
}
