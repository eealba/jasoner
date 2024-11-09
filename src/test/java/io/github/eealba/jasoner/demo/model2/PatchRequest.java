package io.github.eealba.jasoner.demo.model2;

import java.util.List;

/**
 * An array of JSON patch objects to apply partial updates to resources.
 */
public record PatchRequest(List<Patch> value) {

    public PatchRequest(List<Patch> value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        this.value = value;
    }

}
