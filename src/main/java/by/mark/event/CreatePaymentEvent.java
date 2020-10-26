package by.mark.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentEvent {

    private UUID userId;

    private UUID orderId;

    private Double price;

    private String title;
}

