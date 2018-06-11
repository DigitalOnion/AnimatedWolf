package com.outerspace.animatedwolf;

public interface CollisionContract {

    public interface TossCallback {
        public void onToss(float angle, float distance, long duration);
    }

    public interface Collisional {
        public void setCallback(TossCallback callback);
    }

}
