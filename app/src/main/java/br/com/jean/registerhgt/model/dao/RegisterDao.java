package br.com.jean.registerhgt.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.jean.registerhgt.model.Register;

public class RegisterDao {

    private static final List<Register> registers = new ArrayList<>();

    public void addRegister(Register register) {
        this.registers.add(register);
    }

    public List<Register> getAll(boolean asceding) {
        if (asceding) {
            List<Register> list = new ArrayList<>(registers);

            Collections.sort(list, (register1, register2) -> {
                int compare = register2.getDate().compareTo(register1.getDate());
                return compare;
            });

            return list;
        }

        return new ArrayList<>(registers);
    }

}
