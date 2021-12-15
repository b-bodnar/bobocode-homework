package com.bobocode.model;

import com.bobocode.annotation.NewRealization;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@NewRealization(newClass = NewClass.class)
@Data
public class OldClass {
}
