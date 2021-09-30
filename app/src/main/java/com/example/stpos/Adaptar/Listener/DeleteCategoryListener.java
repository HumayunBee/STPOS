package com.example.stpos.Adaptar.Listener;

import android.view.View;
import android.widget.ImageView;

import com.example.stpos.Adaptar.CategoryListAdaptar.CategoryListAdaptar;
import com.example.stpos.DataModel.Category.Category;
import com.example.stpos.Network.Api;

public class DeleteCategoryListener implements View.OnClickListener {
    CategoryListAdaptar categoryListAdaptar;
    CategoryListAdaptar.Viewholders viewholders;
    CategoryListAdaptar.OnItemClickListener listener;
    Category category;
    Api api;

    public DeleteCategoryListener(CategoryListAdaptar categoryListAdaptar, CategoryListAdaptar.Viewholders viewholders,
                                  CategoryListAdaptar.OnItemClickListener listener, Category category) {
        this.categoryListAdaptar = categoryListAdaptar;
        this.viewholders = viewholders;
        this.listener = listener;
        this.category = category;
//        api
    }

    @Override
    public void onClick(View v) {

//        ImageView imageView=new ImageView(viewholders.itemView.getContext(),viewholders.getdelete());



    }
}
