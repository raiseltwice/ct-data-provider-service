package com.ct.dataprovider.data.provider.file.csv.utils;

import com.ct.dataprovider.data.model.CSVCoronavirusDataItem;
import com.ct.entitycommon.entity.CasesPerCountry;
import com.ct.entitycommon.entity.CasesPerState;
import com.ct.entitycommon.entity.Country;
import com.ct.entitycommon.entity.State;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CSVToEntityUtils {

    public static List<CasesPerState> constructCasesPerState(CSVCoronavirusDataItem coronavirusDataItem,
                                                             State state) {
        return coronavirusDataItem.getCasesPerDate().entrySet().stream()
                .map(entry -> new CasesPerState(state, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static List<CasesPerCountry> constructCasesPerCountry(CSVCoronavirusDataItem coronavirusDataItem,
                                                                 Country country) {
        return coronavirusDataItem.getCasesPerDate().entrySet().stream()
                .map(entry -> new CasesPerCountry(country, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static Country constructCountry(CSVCoronavirusDataItem coronavirusDataItem) {
        Country country;
        if (StringUtils.isBlank(coronavirusDataItem.getState())) {
            country = new Country(
                    coronavirusDataItem.getCountry(),
                    coronavirusDataItem.getLatitude(),
                    coronavirusDataItem.getLongitude());
        } else {
            country = new Country(coronavirusDataItem.getCountry(), null, null);
        }

        return country;
    }

    public static State constructState(CSVCoronavirusDataItem coronavirusDataItem, Country country) {
        State state = null;
        if (!StringUtils.isBlank(coronavirusDataItem.getState())) {
            state = new State(
                    coronavirusDataItem.getState(),
                    coronavirusDataItem.getLatitude(),
                    coronavirusDataItem.getLongitude(),
                    country);
        }
        return state;
    }
}
