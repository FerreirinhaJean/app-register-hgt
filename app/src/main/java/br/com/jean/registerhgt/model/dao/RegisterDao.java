package br.com.jean.registerhgt.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.jean.registerhgt.model.Register;

public class RegisterDao {

    private static final List<Register> registers = new ArrayList<>();

    public void addRegister(Register register) {
        this.registers.add(register);
    }

    public List<Register> getAll() {
        return new ArrayList<>(registers);
    }

}
