package com.misc.note.common.domain;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class Captcha {
    private String text;
    private BufferedImage image;

    public Captcha(String text, BufferedImage image) {
        this.text = text;
        this.image = image;
    }
}