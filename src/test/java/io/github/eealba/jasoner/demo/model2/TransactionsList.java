package io.github.eealba.jasoner.demo.model2;

import java.util.List;

public class TransactionsList {

    private final List<Transaction> transactions;
    private final Integer totalItems;
    private final Integer totalPages;
    private final List<LinkDescription> links;

    private TransactionsList(Builder builder) {
        transactions = builder.transactions;
        totalItems = builder.totalItems;
        totalPages = builder.totalPages;
        links = builder.links;

    }

    public List<Transaction> transactions() {
        return transactions;
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

        private List<Transaction> transactions;
        private Integer totalItems;
        private Integer totalPages;
        private List<LinkDescription> links;

        public Builder transactions(List<Transaction> value) {
            transactions = value;
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

        public TransactionsList build() {
            return new TransactionsList(this);
        }

    }

}

