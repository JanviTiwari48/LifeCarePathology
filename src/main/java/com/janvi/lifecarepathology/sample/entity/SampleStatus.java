package com.janvi.lifecarepathology.sample.entity;

public enum SampleStatus {
    PENDING_COLLECTION,
    COLLECTED,
    RECEIVED_AT_LAB,
    REJECTED // e.g. insufficient/contaminated sample — needs recollection
}