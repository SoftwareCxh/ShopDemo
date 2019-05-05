package com.example.graduationproject.ui.mine;

public class MinePresenter implements MineContract.Presenter{
    MineContract.View view;
    public MinePresenter(MineContract.View view){
        this.view=view;
    }

}
