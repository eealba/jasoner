package io.github.eealba.jasoner.demo.model2;

import java.time.Instant;
import java.util.List;

public class PricingScheme {

    private final Integer version;
    private final Money fixedPrice;
    private final PricingModel pricingModel;
    private final List<PricingTier> tiers;
    private final Instant createTime;
    private final Instant updateTime;

    private PricingScheme(Builder builder) {
        version = builder.version;
        fixedPrice = builder.fixedPrice;
        pricingModel = builder.pricingModel;
        tiers = builder.tiers;
        createTime = builder.createTime;
        updateTime = builder.updateTime;

    }

    public Integer version() {
        return version;
    }

    public Money fixedPrice() {
        return fixedPrice;
    }

    public PricingModel pricingModel() {
        return pricingModel;
    }

    public List<PricingTier> tiers() {
        return tiers;
    }

    public Instant createTime() {
        return createTime;
    }

    public Instant updateTime() {
        return updateTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Integer version;
        private Money fixedPrice;
        private PricingModel pricingModel;
        private List<PricingTier> tiers;
        private Instant createTime;
        private Instant updateTime;

        public Builder version(Integer value) {
            version = value;
            return this;
        }

        public Builder fixedPrice(Money value) {
            fixedPrice = value;
            return this;
        }

        public Builder pricingModel(PricingModel value) {
            pricingModel = value;
            return this;
        }

        public Builder tiers(List<PricingTier> value) {
            tiers = value;
            return this;
        }

        public Builder createTime(Instant value) {
            createTime = value;
            return this;
        }

        public Builder updateTime(Instant value) {
            updateTime = value;
            return this;
        }

        public PricingScheme build() {
            return new PricingScheme(this);
        }

    }
    /**
     * The pricing model for tiered plan. The `tiers` parameter is required.
     */
    public enum PricingModel {
        VOLUME,
        TIERED
    }
}

