package com.jdzhang1221.mybatissample.type;

/**
 * @author zhangjundong
 * @date 2020/8/1518:12
 */
public enum Enabled {
//    /**
//     * 禁用
//     */
//    disabled,
//    /**
//     * 启用
//     */
//    enabled;
    /**
     * 启用
     */
    enabled(1),
    /**
     * 禁用
     */
    disabled(0);

    private final int value;

    private Enabled(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}
