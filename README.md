# minidooray-task

## 댓글 등록

- **URL**: `/api/tasks/{taskId}/comments`
- **Method**: POST
- **Host**: localhost:9090
- **Content-Type**: application/json

### 요청 본문(Request Body)

```json
{
  "writerId": 1,
  "contents": "코멘트를 달아보았다3"
}
```

#### 경로 파라미터(Path Parameters)


| 이름       | 타입    | 설명              |
|------------|---------|-------------------|
| taskId  | 숫자   | 작업의 ID    |

#### 요청 본문 파라미터(Request Body Parameters)

| 이름       | 타입    | 설명              |
|------------|---------|-------------------|
| writerId   | 숫자    | 댓글 작성자의 ID |
| contents   | 문자열  | 댓글 내용         |

#### 응답 예시(Response Example)

- **Status Code**: 200 (성공)
