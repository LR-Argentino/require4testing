package org.blackbird.requirefortesting;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModularityTest {

    @Test
    void verifyModularity() {
        var modules = ApplicationModules.of(RequirefortestingApplication.class);

        modules.verify();
        System.out.println(modules);
    }
}
