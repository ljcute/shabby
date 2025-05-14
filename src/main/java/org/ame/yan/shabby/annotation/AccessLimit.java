package org.ame.yan.shabby.annotation;

import java.lang.annotation.*;
/**
 * Author:  ame.yan
 * Date:  2025/5/14 20:44
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    int seconds();

    int maxCount();
}
