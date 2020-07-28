package com.example.graphqMvclClient.model;


import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Value
//==final @ToString @EqualsAndHashCode @AllArgsConstructor
public class CompassId implements Serializable {

    private static final long serialVersionUID = 7661028081075341454L;

    String entityId;

    @NonNull
    String workingSetId;

}
