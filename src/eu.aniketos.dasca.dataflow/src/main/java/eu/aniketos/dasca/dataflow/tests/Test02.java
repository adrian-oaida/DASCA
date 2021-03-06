/*
 * (C) Copyright 2010-2015 SAP SE.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */

package eu.aniketos.dasca.dataflow.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eu.aniketos.dasca.dataflow.util.SuperGraphUtil;

public class Test02 {

    String entryClass = "Test02";

    @Test
    public void testBad() {
        AllTests.init();
        int result = SuperGraphUtil.analyzeAndSaveSuperGraph(AllTests.superGraph, entryClass, "bad");
        assertEquals(1, result);
        return;
    }

    @Test
    public void testGood01() {
        AllTests.init();
        int result = SuperGraphUtil.analyzeAndSaveSuperGraph(AllTests.superGraph, entryClass, "good01");
        assertEquals(0, result);
        return;
    }

    @Test
    public void testGood02() {
        AllTests.init();
        int result = SuperGraphUtil.analyzeAndSaveSuperGraph(AllTests.superGraph, entryClass, "good02");
        assertEquals(0, result);
        return;
    }

}
