package br.com.jean.registerhgt.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.jean.registerhgt.R;
import br.com.jean.registerhgt.model.Register;
import br.com.jean.registerhgt.model.dao.GroupedItems;
import br.com.jean.registerhgt.model.dao.RegisterDao;

public class RegisterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Register> registers = new ArrayList<>();
    private Context context;
    private RegisterDao registerDao = new RegisterDao();

    private List<GroupedItems> groupedItemsList;
    // Tipos de visualização
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public RegisterAdapter(Context context) {
        this.context = context;
    }

    // Determina o tipo de view (cabeçalho ou item)
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    private boolean isPositionHeader(int position) {
        // Verifica se a posição corresponde a um cabeçalho (data)
        int currentPosition = 0;
        for (GroupedItems group : groupedItemsList) {
            if (position == currentPosition) {
                return true;  // Posição de cabeçalho
            }
            currentPosition += group.getItems().size() + 1;  // 1 para o cabeçalho e o resto para os itens
        }
        return false;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            // Inflar o layout do cabeçalho
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            // Inflar o layout do item
            View view = LayoutInflater.from(context).inflate(R.layout.activity_main_item_adapter, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_HEADER) {
            GroupedItems group = getGroupForPosition(position);
            ((HeaderViewHolder) holder).bind(group);
        } else {
            Register register = getRegisterForPosition(position);
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.tvValorGlicemia.setText(register.getFormattedValue());
            itemViewHolder.tvDataGlicemia.setText(register.getFormattedDate());
            itemViewHolder.tvTipoGlicemia.setText(getFormattedType(register));
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (GroupedItems group : groupedItemsList) {
            count += group.getItems().size() + 1; // 1 para o cabeçalho + itens
        }
        return count;
    }

    // Método para atualizar os dados e agrupar
    public void update() {
        List<Register> allRegisters = registerDao.getAll(true);
        List<GroupedItems> groupedItems = groupItemsByDate(allRegisters);
        this.groupedItemsList = groupedItems;
        notifyDataSetChanged();
    }

    // Agrupando os itens pela data
    private List<GroupedItems> groupItemsByDate(List<Register> registers) {
        Map<String, List<Register>> groupedMap = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Agrupar itens pela data
        for (Register register : registers) {
            String formattedDate = dateFormat.format(register.getDate());
            if (!groupedMap.containsKey(formattedDate)) {
                groupedMap.put(formattedDate, new ArrayList<>());
            }
            groupedMap.get(formattedDate).add(register);
        }

        // Converter o Map para a lista de GroupedItems
        List<GroupedItems> groupedItemsList = new ArrayList<>();
        for (Map.Entry<String, List<Register>> entry : groupedMap.entrySet()) {
            try {
                Date date = dateFormat.parse(entry.getKey());
                groupedItemsList.add(new GroupedItems(date, entry.getValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Collections.sort(groupedItemsList, (register1, register2) -> {
            int compare = register2.getData().compareTo(register1.getData());
            return compare;
        });

        return groupedItemsList;
    }

    // Método auxiliar para obter o GroupedItems para uma determinada posição
    private GroupedItems getGroupForPosition(int position) {
        int currentPosition = 0;
        for (GroupedItems group : groupedItemsList) {
            currentPosition++;  // Conta o cabeçalho
            if (position == currentPosition - 1) {
                return group;
            }
            currentPosition += group.getItems().size();
        }
        return null;
    }

    // Método auxiliar para obter o Register para uma posição específica
    private Register getRegisterForPosition(int position) {
        int currentPosition = 0;
        for (GroupedItems group : groupedItemsList) {
            currentPosition++;  // Conta o cabeçalho
            if (position < currentPosition + group.getItems().size()) {
                return group.getItems().get(position - currentPosition);
            }
            currentPosition += group.getItems().size();
        }
        return null;
    }

    // Método para formatar o tipo do registro
    private String getFormattedType(Register register) {
        Resources resources = context.getResources();
        String[] types = resources.getStringArray(R.array.list_type_register);
        return types[register.getType()];
    }

    // ViewHolder para os itens
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvValorGlicemia, tvDataGlicemia, tvTipoGlicemia;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvValorGlicemia = itemView.findViewById(R.id.activity_main_item_adapter_valor_glicemia);
            tvDataGlicemia = itemView.findViewById(R.id.activity_main_item_adapter_data);
            tvTipoGlicemia = itemView.findViewById(R.id.activity_main_item_adapter_tipo);
        }
    }

    // ViewHolder para o cabeçalho
    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView dateTextView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.tvDateHeader);
        }

        public void bind(GroupedItems group) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            dateTextView.setText(dateFormat.format(group.getData()));
        }
    }
}
