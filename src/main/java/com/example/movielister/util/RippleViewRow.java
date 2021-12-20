package com.example.movielister.util;




import javafx.scene.control.Skin;
import javafx.scene.control.skin.ListCellSkin;


/**
 * Created by Felipe on 03/11/2015.
 */
public class RippleViewRow extends RecyclerView.ViewRow {

    public RippleViewRow(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        ListCellSkin listCellSkin=new ListCellSkin(this);
        RippleSkinFactory.getRippleEffect(listCellSkin, this);
        return listCellSkin;
    }
}

