# In-Memory Cache Service

In-Memory Cache 기능을 갖는 Cache 객체 및 Rest API Service

## Getting Started

### Prerequisites

JDK 1.8 or later

### Running

```shell
./gradlew bootRun
```

## Rest API

### 카테고리 관리

#### 목록

```
GET /categories
```

#### 추가

```
POST /categories
```

#### 수정

```
PATCH /categories/{categoryNo}
```

#### 삭제

```
DELETE /categories/{categoryNo}
```

#### 카테고리에 대한 상품 목록 조회

```
GET /categories/{categoryNo}/products
```

### 상품 관리

#### 조회

```
GET /products/{productNo}
```

#### 추가

```
POST /products
```

#### 수정

```
PATCH /products/{productNo}
```

#### 삭제

```
DELETE /products/{productNo}
```
