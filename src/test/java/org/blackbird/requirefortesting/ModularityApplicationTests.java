package org.blackbird.requirefortesting;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModularityApplicationTests {


  @Test
  void verifyModules() {
    var modules = ApplicationModules.of(RequirefortestingApplication.class);

    modules.verify();

    System.out.println("Modules in the application:");
    System.out.println(modules);
    new Documenter(modules)
        .writeIndividualModulesAsPlantUml()
        .writeModulesAsPlantUml();
  }
}
