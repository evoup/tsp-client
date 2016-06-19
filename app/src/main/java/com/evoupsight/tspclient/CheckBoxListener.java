package com.evoupsight.tspclient;

import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by evoup on 16-6-19.
 */
public class CheckBoxListener implements CheckBox.OnCheckedChangeListener {
    MainActivity a;

    public CheckBoxListener(MainActivity a) {
        this.a = a;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        // 选中哪些
        StringBuffer str = new StringBuffer("");
        int selIdx = 0;
        if (a.box1.isChecked()) {
            str.append("A_");
            selIdx++;
        }
        if (a.box2.isChecked()) {
            str.append("B_");
            selIdx++;
        }
        if (a.box3.isChecked()) {
            str.append("C_");
            selIdx++;
        }
        if (a.box4.isChecked()) {
            str.append("D_");
            selIdx++;
        }
        if (a.box5.isChecked()) {
            str.append("E_");
            selIdx++;
        }
        if (a.box6.isChecked()) {
            str.append("F_");
            selIdx++;
        }
        if (a.box7.isChecked()) {
            str.append("G_");
            selIdx++;
        }
        if (a.box8.isChecked()) {
            str.append("H_");
            selIdx++;
        }
        if (a.box9.isChecked()) {
            str.append("I_");
            selIdx++;
        }
        if (a.box10.isChecked()) {
            str.append("J_");
            selIdx++;
        }
        a.setChromosomeStr(str.toString().substring(0, str.toString().length() - 1));
        a.setSelectLocation(selIdx);
    }
}
