package com.honey.e_commerce.fenclass;

public class IClassifyPresenter {
    private IClassifyView iClassifyView;
    private IClassifyModle iClassifyModle;
    public IClassifyPresenter(IClassifyView iClassifyView)
    {
        this.iClassifyView=iClassifyView;
        iClassifyModle=new ClassifyModle();
    }
    public void setClassifyFirst(IClassifyView iClassifyView)
    {
        iClassifyModle.classify_1(iClassifyView);
    }
}
