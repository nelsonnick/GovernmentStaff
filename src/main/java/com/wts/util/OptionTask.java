package com.wts.util;

import com.jfinal.kit.PropKit;

import java.io.File;

import static com.wts.controller.DepartmentController.createCascaderOptions;

public class OptionTask implements Runnable {
    @Override
    public void run() {
        File file = new File("d:\\op.txt");
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        createCascaderOptions();
        PropKit.use(file).get("options");
    }
}
