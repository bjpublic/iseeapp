package com.charlezz.dagger2.dagger.test.cafe;

public class Machine {

    private MachineComponent component;

    public Machine(MachineComponent.Builder builder) {

        component = builder.setMachineModule(new MachineModule()).build();

        component.inject(this);

    }

    public Coffee extract() {
        return component.getCoffee();

    }

}