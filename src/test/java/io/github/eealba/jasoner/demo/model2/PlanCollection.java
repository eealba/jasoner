package io.github.eealba.jasoner.demo.model2;

import java.util.List;

public class PlanCollection {

    private final List<Plan> plans;
    private final Integer totalItems;
    private final Integer totalPages;
    private final List<LinkDescription> links;

    private PlanCollection(Builder builder) {
        plans = builder.plans;
        totalItems = builder.totalItems;
        totalPages = builder.totalPages;
        links = builder.links;

    }

    public List<Plan> plans() {
        return plans;
    }

    public Integer totalItems() {
        return totalItems;
    }

    public Integer totalPages() {
        return totalPages;
    }

    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<Plan> plans;
        private Integer totalItems;
        private Integer totalPages;
        private List<LinkDescription> links;

        public Builder plans(List<Plan> value) {
            plans = value;
            return this;
        }

        public Builder totalItems(Integer value) {
            totalItems = value;
            return this;
        }

        public Builder totalPages(Integer value) {
            totalPages = value;
            return this;
        }

        public Builder links(List<LinkDescription> value) {
            links = value;
            return this;
        }

        public PlanCollection build() {
            return new PlanCollection(this);
        }

    }

}

