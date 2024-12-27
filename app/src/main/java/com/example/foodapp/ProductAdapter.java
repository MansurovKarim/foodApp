package com.example.foodapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.databinding.FoodItemBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(ProductItem item);
    }

    private List<ProductItem> productList;
    private OnItemClickListener listener;
    private int selectedPosition = RecyclerView.NO_POSITION; // Для отслеживания выбранного элемента

    public ProductAdapter(List<ProductItem> productList, OnItemClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FoodItemBinding binding = FoodItemBinding.inflate(inflater, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductItem item = productList.get(position);
        holder.bind(item, listener);

        // Изменение фона в зависимости от выбранной позиции
        if (position == selectedPosition) {
            holder.binding.getRoot().setBackgroundColor(
                    holder.binding.getRoot().getContext().getResources().getColor(R.color.red)
            );
        } else {
            holder.binding.getRoot().setBackgroundColor(
                    holder.binding.getRoot().getContext().getResources().getColor(R.color.gray)
            );
        }

        // Установка клика для обновления выбранной позиции
        holder.binding.getRoot().setOnClickListener(v -> {
            int previousPosition = selectedPosition;
            selectedPosition = position;

            // Уведомляем адаптер об изменении старой и новой позиции
            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);

            // Вызываем слушатель
            listener.onItemClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final FoodItemBinding binding;

        public ProductViewHolder(@NonNull FoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ProductItem item, OnItemClickListener listener) {
            binding.name.setText(item.getName());
            Glide.with(binding.getRoot().getContext()).load(item.getImage()).into(binding.image);
            binding.price.setText("$" + item.getPrice());
        }
    }
}