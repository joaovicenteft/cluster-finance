package com.example.finance.controller;

import com.example.finance.model.DataEntry;
import com.example.finance.model.EntityFinance;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.*;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    private final Map<Long, List<String>> financeMap = new HashMap<>();
    private final List<DataEntry> financeList = new ArrayList<>();
    private long idCounter = 1;

    @GetMapping
    public ResponseEntity<List<EntityFinance>> getAllFinance() {
        List<EntityFinance> entityFinance = new ArrayList<>();
        System.out.println(financeList);
        for (DataEntry entry : financeList) {
            entityFinance.add(new EntityFinance(Long.parseLong(entry.getValues(0)), Integer.parseInt(entry.getValues(1)),
                    Double.parseDouble(entry.getValues(2))));
        }

        return ResponseEntity.ok(entityFinance);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EntityFinance> getFinanceById(@PathVariable Long id) {
        //List<String> message = financeMap.get(id);
        //System.out.println(financeList.get(id.intValue()-1).getValues(0));

        String id_financeList = null;
        String timeMonth = null;
        String inflationMean = null;

        for (int i = 0; i < financeList.size(); i++) {
            //System.out.println(financeList.get(i).getValues(0));
            
            if (Long.parseLong(financeList.get(i).getValues(0)) == id) {
                id_financeList = financeList.get(i).getValues(0); // equivalent to take position id with index
                timeMonth = financeList.get(i).getValues(1); // values
                inflationMean = financeList.get(i).getValues(2); // more values
            }
        }
        

        if (timeMonth != null) {
            return ResponseEntity.ok(new EntityFinance(Long.parseLong(id_financeList), Integer.parseInt(timeMonth), Double.parseDouble(inflationMean)));
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<EntityFinance> createFinance(@RequestBody EntityFinance entityfinance) {
        //List<String> values = Arrays.asList(entityfinance.getValues(), entityfinance.getTimeMonth());
        //financeMap.put(idCounter, values);
        financeList.add(new DataEntry(String.valueOf(idCounter), String.valueOf(entityfinance.getTimeMonth()), String.valueOf(entityfinance.getInflationMean())));
        entityfinance.setId(idCounter);
        idCounter++;
        return ResponseEntity.ok(entityfinance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityFinance> updateFinance(@PathVariable Long id, @RequestBody EntityFinance entityfinance) {
        if (financeList.get(id.intValue()) != null) {
            financeList.get(id.intValue()).putValues(id.intValue(), String.valueOf(entityfinance.getTimeMonth()));
            return ResponseEntity.ok(new EntityFinance(id, entityfinance.getTimeMonth(), entityfinance.getInflationMean()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinance(@PathVariable Long id) {

        if (financeList.get((id.intValue())-1) != null)  {
            //financeList.get(id.intValue()-1).deleteValues(id.intValue()-1);
            financeList.remove(id.intValue()-1);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}