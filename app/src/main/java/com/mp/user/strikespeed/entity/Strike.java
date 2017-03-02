package com.mp.user.strikespeed.entity;

import java.util.Date;

public class Strike {

    private String type;
    private boolean isRight;
    private boolean hasGlove;
    private boolean hasStep;
    private Date date;

    private double strikeSpeed;
    private double reactionSpeed;
    private double acceleration;

    public Strike(String type, boolean isRight, boolean hasGlove, boolean hasStep) {
        this.type = type;
        this.isRight = isRight;
        this.hasGlove = hasGlove;
        this.hasStep = hasStep;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public boolean isHasGlove() {
        return hasGlove;
    }

    public void setHasGlove(boolean hasGlove) {
        this.hasGlove = hasGlove;
    }

    public boolean isHasStep() {
        return hasStep;
    }

    public void setHasStep(boolean hasStep) {
        this.hasStep = hasStep;
    }

    public double getStrikeSpeed() {
        return strikeSpeed;
    }

    public void setStrikeSpeed(double strikeSpeed) {
        this.strikeSpeed = strikeSpeed;
    }

    public double getReactionSpeed() {
        return reactionSpeed;
    }

    public void setReactionSpeed(double reactionSpeed) {
        this.reactionSpeed = reactionSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

