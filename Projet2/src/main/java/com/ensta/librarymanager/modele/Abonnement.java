package com.ensta.librarymanager.modele;

public enum Abonnement {
    BASIC(2), PREMIUM(5), VIP(20);
    int maxCount;

    Abonnement(int c){
        maxCount = c;
    }

    public int getMaxCount(){
        return maxCount;
    }
}
