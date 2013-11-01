package com.mquesada.friendsmap.util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public enum CountryColorHelper {

    INSTANCE;

    private Map<String, String> regionColor = new HashMap<String, String>(15, 1);
    private Map<String, String> countriesByRegion = new HashMap<String, String>(250, 1);

    private CountryColorHelper() {
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadFile() throws FileNotFoundException {
        String[] colors = {"FAAC58", "B4045F", "FF00FF", "8000FF", "4000FF", "8A62B3", "088A08", "0404B4", "B40404", "FE2E2E", "40FF00", "A9A9F5", "848484", "086A87"};
        int idx = 0;
        Scanner scanner = new Scanner(this.getClass().getClassLoader()
                .getResourceAsStream("countries_by_region.txt"));
        String region = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("*")) {
                region = line;
                if (!regionColor.containsKey(region)) {
                    regionColor.put(region, colors[idx]);
                    idx++;
                }
            } else {
                countriesByRegion.put(line, region);
            }
        }
    }

    public String getCountryColorCode(String countryName) {
        if (countriesByRegion.containsKey(countryName)) {
            return regionColor.get(countriesByRegion.get(countryName));
        }
        return "F5A9F2";
    }

}
