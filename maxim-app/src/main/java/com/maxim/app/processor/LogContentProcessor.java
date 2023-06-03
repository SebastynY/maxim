package com.maxim.app.processor;

import org.apache.nifi.annotation.lifecycle.OnScheduled;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.processor.ProcessorInitializationContext;
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.Relationship;
import org.apache.nifi.processor.exception.ProcessException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Tags({"example", "custom", "log", "demo"})
@CapabilityDescription("This processor logs the content of incoming FlowFiles.")
public class LogContentProcessor extends AbstractProcessor {
    private Set<Relationship> relationships;

    @Override
    protected void init(final ProcessorInitializationContext context) {
        final Set<Relationship> relationships = new HashSet<>();
        relationships.add(new Relationship.Builder().name("success").build());
        this.relationships = Collections.unmodifiableSet(relationships);
    }

    @Override
    public Set<Relationship> getRelationships() {
        return this.relationships;
    }

    @Override
    public final List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return Collections.emptyList();
    }

    @OnScheduled
    public void onScheduled(final ProcessContext context) {

    }

    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) throws ProcessException {
        session.read(session.get(), inputStream -> {
            String content = new String(inputStream.readAllBytes());
            getLogger().info(content);
        });
        session.transfer(session.get(), relationships.iterator().next());
    }
}
