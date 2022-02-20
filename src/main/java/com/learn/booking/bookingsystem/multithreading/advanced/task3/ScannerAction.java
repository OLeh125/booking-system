package com.learn.booking.bookingsystem.multithreading.advanced.task3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;

@Getter
public class ScannerAction extends RecursiveAction {

    private File file;
    private AtomicLong fileCount;
    private AtomicLong dirCount;

    public ScannerAction(File file) {
        this(file, new AtomicLong(), new AtomicLong());
    }

    public ScannerAction(File file, AtomicLong fileCount, AtomicLong dirCount) {
        this.file = file;
        this.fileCount = fileCount;
        this.dirCount = dirCount;
    }

    @Override
    protected void compute() {
        if (file.isFile()) {
            fileCount.getAndIncrement();
        } else {
            dirCount.getAndIncrement();
            final File[] children = file.listFiles();
            if (children != null) {
                List<ScannerAction> actions = new ArrayList<>();
                for (final File child : children) {
                    actions.add(new ScannerAction(child, fileCount, dirCount));
                }
                invokeAll(actions);
            }
        }
    }

}
