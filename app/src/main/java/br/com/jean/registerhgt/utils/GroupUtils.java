package br.com.jean.registerhgt.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.jean.registerhgt.model.Register;
import br.com.jean.registerhgt.model.dao.GroupedItems;

public class GroupUtils {

    public static List<GroupedItems> groupItemsByDate(List<Register> items) {
        Map<String, List<Register>> groupedMap = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Agrupar itens por data formatada
        for (Register item : items) {
            String formattedDate = dateFormat.format(item.getDate());
            if (!groupedMap.containsKey(formattedDate)) {
                groupedMap.put(formattedDate, new ArrayList<>());
            }
            groupedMap.get(formattedDate).add(item);
        }

        // Converter para a lista de GroupedItems
        List<GroupedItems> groupedItemsList = new ArrayList<>();
        for (Map.Entry<String, List<Register>> entry : groupedMap.entrySet()) {
            try {
                Date date = dateFormat.parse(entry.getKey());
                groupedItemsList.add(new GroupedItems(date, entry.getValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return groupedItemsList;
    }
}
