package com.winestore.api.dto.cart;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winestore.enums.TrackingStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class CartHistoryDTO extends UserCartDTO {

    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime buyDate;

    private TrackingStatus trackingStatus;
}
