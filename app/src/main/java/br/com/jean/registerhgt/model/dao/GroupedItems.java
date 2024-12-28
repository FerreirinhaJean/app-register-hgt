package br.com.jean.registerhgt.model.dao;

import java.util.Date;
import java.util.List;

import br.com.jean.registerhgt.model.Register;

public class GroupedItems {
    private Date data;
    private List<Register> items;

    public GroupedItems(Date data, List<Register> items) {
        this.data = data;
        this.items = items;
    }

    public Date getData() {
        return data;
    }

    public List<Register> getItems() {
        return items;
    }


}
