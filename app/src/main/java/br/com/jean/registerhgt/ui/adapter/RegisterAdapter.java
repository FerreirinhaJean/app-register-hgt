package br.com.jean.registerhgt.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.jean.registerhgt.R;
import br.com.jean.registerhgt.model.Register;
import br.com.jean.registerhgt.model.dao.RegisterDao;

public class RegisterAdapter extends RecyclerView.Adapter<RegisterAdapter.ViewHolder> {

    private List<Register> registers = new ArrayList<>();
    private Context context;
    private RegisterDao registerDao = new RegisterDao();

    public RegisterAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Register register = registers.get(position);

        holder.tvValorGlicemia.setText(register.getFormattedValue());
        holder.tvDataGlicemia.setText(register.getFormattedDate());
        holder.tvTipoGlicemia.setText(getFormattedType(register));
    }

    @Override
    public int getItemCount() {
        return registers.size();
    }

    public void update() {
        registers.clear();
        registers.addAll(registerDao.getAll());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvValorGlicemia, tvDataGlicemia, tvTipoGlicemia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvValorGlicemia = itemView.findViewById(R.id.activity_main_item_adapter_valor_glicemia);
            tvDataGlicemia = itemView.findViewById(R.id.activity_main_item_adapter_data);
            tvTipoGlicemia = itemView.findViewById(R.id.activity_main_item_adapter_tipo);
        }
    }

    private String getFormattedType(Register register) {
        Resources resources = context.getResources();
        String[] types = resources.getStringArray(R.array.list_type_register);
        return types[register.getType()];
    }

}
