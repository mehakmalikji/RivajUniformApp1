package com.rivaj.uniform;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout container;
    private TextView totalPiecesText, totalPriceText;
    private List<ItemRow> itemRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(container);

        // Title
        TextView title = new TextView(this);
        title.setText("Rivaj School Uniform Inventory");
        title.setTextSize(20);
        title.setPadding(20, 20, 20, 20);
        container.addView(title);

        // Totals Box
        totalPiecesText = new TextView(this);
        totalPriceText = new TextView(this);
        totalPiecesText.setText("Total Pieces: 0");
        totalPriceText.setText("Total Price: Rs 0");

        container.addView(totalPiecesText);
        container.addView(totalPriceText);

        // Inventory Items (Example Category: Black Pant)
        addCategoryTitle("Black Pant");
        addItemRow(22, 6, 255);
        addItemRow(24, 14, 310);
        addItemRow(26, 9, 310);
        addItemRow(28, 9, 310);
        addItemRow(30, 6, 310);
        addItemRow(32, 5, 310);
        addItemRow(34, 14, 310);
        addItemRow(36, 12, 420);
        addItemRow(38, 9, 420);

        // Example Category: Blue Shirt
        addCategoryTitle("Blue Shirt");
        addItemRow(20, 8, 310);
        addItemRow(22, 10, 370);
        addItemRow(24, 16, 370);
        addItemRow(26, 12, 370);
        addItemRow(28, 16, 370);
        addItemRow(30, 6, 440);
        addItemRow(32, 6, 440);

        // Add more categories here...
        // e.g. Denzy Pant, Hajvery Grey Pant etc.

        // Calculate Button
        Button calcButton = new Button(this);
        calcButton.setText("Recalculate Totals");
        container.addView(calcButton);

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recalcTotals();
            }
        });

        itemRows = new ArrayList<>();

        setContentView(scrollView);
    }

    private void addCategoryTitle(String name) {
        TextView catTitle = new TextView(this);
        catTitle.setText(name);
        catTitle.setTextSize(18);
        catTitle.setPadding(10, 20, 10, 10);
        container.addView(catTitle);
    }

    private void addItemRow(int size, int qty, int price) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);

        TextView sizeText = new TextView(this);
        sizeText.setText("Size " + size);
        sizeText.setPadding(10, 10, 10, 10);
        row.addView(sizeText);

        EditText qtyInput = new EditText(this);
        qtyInput.setHint("Qty");
        qtyInput.setText(String.valueOf(qty));
        qtyInput.setEms(3);
        row.addView(qtyInput);

        EditText priceInput = new EditText(this);
        priceInput.setHint("Price");
        priceInput.setText(String.valueOf(price));
        priceInput.setEms(4);
        row.addView(priceInput);

        container.addView(row);

        itemRows.add(new ItemRow(qtyInput, priceInput));
    }

    private void recalcTotals() {
        int totalPieces = 0;
        int totalPrice = 0;

        for (ItemRow row : itemRows) {
            int qty = 0;
            int price = 0;

            try {
                qty = Integer.parseInt(row.qtyInput.getText().toString());
            } catch (Exception ignored) {}

            try {
                price = Integer.parseInt(row.priceInput.getText().toString());
            } catch (Exception ignored) {}

            totalPieces += qty;
            totalPrice += (qty * price);
        }

        totalPiecesText.setText("Total Pieces: " + totalPieces);
        totalPriceText.setText("Total Price: Rs " + totalPrice);
    }

    // Helper class to hold each row
    private static class ItemRow {
        EditText qtyInput;
        EditText priceInput;

        ItemRow(EditText q, EditText p) {
            this.qtyInput = q;
            this.priceInput = p;
        }
    }
}
