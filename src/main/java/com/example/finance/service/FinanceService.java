package com.example.finance.service;

import com.example.finance.model.DataEntry;
import com.example.finance.model.EntityFinance;
import com.example.finance.repository.FinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
public class FinanceService {

    @Autowired
    FinanceRepository financeRepo;

    public List<EntityFinance> findAll(List<DataEntry> financeList, List<EntityFinance> entityFinance) {
        for (DataEntry entry : financeList) {
            entityFinance.add(new EntityFinance(Long.parseLong(entry.getValues(0)), Integer.parseInt(entry.getValues(1)),
                    Double.parseDouble(entry.getValues(2)), Double.parseDouble(entry.getValues(3))));
        }
        return entityFinance;
    }

    public List<EntityFinance> findSpecificId(List<DataEntry> financeList, Long id, List<EntityFinance> entityFinance) {
        //List<String> message = financeMap.get(id);
        //System.out.println(financeList.get(id.intValue()-1).getValues(0));
        String id_financeList = null;
        String timeMonth = null;
        String inflationMean = null;
        String fullMoney = null;

        for (int i = 0; i < financeList.size(); i++) {
            //System.out.println(financeList.get(i).getValues(0));

            if (Long.parseLong(financeList.get(i).getValues(0)) == id) {
                id_financeList = financeList.get(i).getValues(0); // equivalent to take position id with index
                timeMonth = financeList.get(i).getValues(1); // values
                inflationMean = financeList.get(i).getValues(2); // more values
                fullMoney = financeList.get(i).getValues(3);
            }
        }

        entityFinance.add(new EntityFinance(Long.parseLong(id_financeList), Integer.parseInt(timeMonth)
                , Double.parseDouble(inflationMean), Double.parseDouble(fullMoney)));

        if (timeMonth != null) {
            return entityFinance;
        }
        return null;
        //String strid = String.valueOf(id);
        //List<String> stringList = new ArrayList<>();

        //for (int i = 0; i < strid.length(); i++) {
        //  stringList.add(Character.toString(strid.charAt(i)));
        //}

        //Iterable<String> stringIterable = stringList;
        //financerepo.findAllById(stringIterable);
    }

    public EntityFinance putData(List<DataEntry> financeList, Long id, EntityFinance entityfinance) {

        if (financeList.get(id.intValue()) != null) {
            financeList.get(id.intValue()).putValues(id.intValue(), String.valueOf(entityfinance.getTimeMonth()));
            return (new EntityFinance(id, entityfinance.getTimeMonth(), entityfinance.getInflationMean(), entityfinance.getFullMoney()));
        }
        return null;
    }

    public EntityFinance postData(List<DataEntry> financeList, EntityFinance entityfinance, Long idCounter) {
        //List<String> values = Arrays.asList(entityfinance.getValues(), entityfinance.getTimeMonth());
        //financeMap.put(idCounter, values);
        financeList.add(new DataEntry(String.valueOf(idCounter),
                String.valueOf(entityfinance.getTimeMonth()), String.valueOf(entityfinance.getInflationMean()), String.valueOf(entityfinance.getFullMoney())));
        entityfinance.setId(idCounter);

        return entityfinance;
        //financerepo.save(entityfinance);
    }

    public int deleteData(List<DataEntry> financeList, Long id) {
        if (financeList.get((id.intValue())-1) != null)  {
            //financeList.get(id.intValue()-1).deleteValues(id.intValue()-1);
            financeList.remove(id.intValue()-1);
            return 1;
        }
        return 0;
    }
}
