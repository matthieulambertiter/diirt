/*
 * Copyright 2008-2010 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */

package org.epics.pvmanager.extra;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.epics.pvmanager.util.TimeDuration;

/**
 *
 * @author carcassi
 */
public class WaterfallPlotParameters extends Parameters {

    static class InternalCopy {
        final Integer height;
        final ColorScheme colorScheme;
        final Boolean adaptiveRange;
        final TimeDuration pixelDuration;
        
        private InternalCopy(Map<Object, Object> params) {
            height = (Integer) params.get("height");
            colorScheme = (ColorScheme) params.get("colorScheme");
            adaptiveRange = (Boolean) params.get("adaptiveRange");
            pixelDuration = (TimeDuration) params.get("pixelDuration");
        }
        
    }

    WaterfallPlotParameters(Object name, Object value) {
        super(Collections.singletonMap(name, value));
    }

    WaterfallPlotParameters(Map<Object, Object> map) {
        super(map);
    }

    WaterfallPlotParameters(WaterfallPlotParameters defaults, WaterfallPlotParameters... newValues) {
        super(defaults, newValues);
    }

    static WaterfallPlotParameters defaults() {
        Map<Object, Object> defaults = new HashMap<Object, Object>();
        defaults.put("height", 50);
        defaults.put("colorScheme", ColorScheme.singleRangeGradient(Color.BLACK, Color.WHITE));
        defaults.put("adaptiveRange", false);
        defaults.put("pixelDuration", TimeDuration.ms(10));
        return new WaterfallPlotParameters(defaults);
    }
    
    InternalCopy internalCopy() {
        return new InternalCopy(getParameters());
    }
    
    public static WaterfallPlotParameters height(int height) {
        return new WaterfallPlotParameters("height", height);
    }

    public static WaterfallPlotParameters colorScheme(ColorScheme colorScheme) {
        return new WaterfallPlotParameters("colorScheme", colorScheme);
    }

    public static WaterfallPlotParameters adaptiveRange(boolean adaptiveRange) {
        return new WaterfallPlotParameters("adaptiveRange", adaptiveRange);
    }

    
}
