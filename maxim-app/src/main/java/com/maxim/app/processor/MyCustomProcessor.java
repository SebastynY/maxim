package com.maxim.app.processor;

import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;

public class MyCustomProcessor extends AbstractProcessor {
    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) {
        // This method is called when data is to be processed.
    }
}
