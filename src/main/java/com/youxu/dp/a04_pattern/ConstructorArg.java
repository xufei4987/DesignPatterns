package com.youxu.dp.a04_pattern;

/**
 * 当 isRef 为 true 的时候，arg 表示 String 类型的 refBeanId，type 不需要设置
 * 当 isRef 为 false 的时候，arg、type 都需要设置
 */
public class ConstructorArg {
    private boolean isRef;
    private Class type;
    private Object arg;

    private ConstructorArg(boolean isRef, Class type, Object arg) {
        this.isRef = isRef;
        this.type = type;
        this.arg = arg;
    }

    public static class Builder {
        private boolean isRef;
        private Class type;
        private Object arg;

        public ConstructorArg build() {
            if (isRef) {
                if (!(arg instanceof String)) {
                    throw new IllegalArgumentException("arg's type must be String");
                }
                if (type != null) {
                    throw new IllegalArgumentException("type must be null");
                }
                return new ConstructorArg(isRef, null, arg);
            } else {
                if (arg == null || type == null) {
                    throw new IllegalArgumentException("arg and type must not be null");
                }
                return new ConstructorArg(isRef, type, arg);
            }
        }

        public Builder setRef(boolean ref) {
            isRef = ref;
            return this;
        }

        public Builder setType(Class type) {
            this.type = type;
            return this;
        }

        public Builder setArg(Object arg) {
            this.arg = arg;
            return this;
        }
    }
}

