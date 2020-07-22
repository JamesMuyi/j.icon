package com.xdough.image.processor;

/**
 * java类简单作用描述
 *
 * @author: James
 * CreateDate:     2018-12-06 18:53
 */
public enum CustomColor {
    WHITE(0xffffff, "白色", "white"),
    GRAY(0x7e7e7e, "白色", "gray"),
    MEGAEASE_RED(0xc57c76, "megaease红", "red"),
    MEGAEASE_GREEN(0x7cc576, "megaease绿", "green"),
    MEGAEASE_BLUE(0x49b5e7, "megaease蓝", "blue"),
    MEGAEASE_BLACK(0x222222, "megaease深灰", "black"),
    MEGAEASE_TEXT_WHITE_LIGHT_GRAY(0xe8e8e8, "megaease字体白浅灰", "text_white_light_gray"),
    MEGAEASE_TEXT_WHITE_DARK_GRAY(0xa9a9a9, "megaease字体白深灰", "text_white_dark_gray"),
    MEGAEASE_TEXT_DARK_GRAY(0x9d9d9d, "megaease字体黑深灰，并且用于灰色图标", "text_dark_gray"),
    MEGAEASE_BACKGROUND_GRAY(0x303035, "背景灰", "background_gray"),
    DISNEY_LIGHT_BLUE(0x4a91cf, "迪士尼按钮用浅蓝色", "disney_light_blue"),
    DISNEY_LIGHT_GRAY(0xd2d5da, "迪士尼按钮用浅灰色", "disney_light_gray"),


    DISNEY_TAB_ON(0x1B242F, "迪士尼tab on", "disney_tab_on"),
    DISNEY_TAB_OFF(0x8491A8, "迪士尼tab off", "disney_tab_off"),

    MINI_PROGRAM_TICKET_RED(0xff495a, "门票小程序红色", "ticket_red");


    private int value;

    private String desc;

    private String name;

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    CustomColor(int value, String desc, String name) {
        this.value = value;
        this.desc = desc;
        this.name = name;
    }
}
