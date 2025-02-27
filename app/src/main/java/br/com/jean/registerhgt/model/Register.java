package br.com.jean.registerhgt.model;

import android.content.res.Resources;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import br.com.jean.registerhgt.R;

public class Register {

    private Long id;
    private Integer value;
    private Date date;
    private Integer type;
    private String observacoes;

    public Register(long id, int value, Date date, int type, String observacoes) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.type = type;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public String getFormattedValue() {
        return "Glicemia: " + this.value + " (mg/dL)";
    }

    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        return sdf.format(this.date);
    }

}
