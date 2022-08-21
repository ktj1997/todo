import { useState } from "react";
import { saveTodo } from "../libs/apis/TodoApi";

const Form = (props) => {
  const { setTodos, nextSequence } = props;

  const [input, setInput] = useState("");
  const onChangeInputHandler = (e) => {
    setInput(e.target.value);
  };
  const onSubmitHandler = (e) => {
    e.preventDefault();
    const request = {
      name: input,
      isChecked: false,
      priority: nextSequence
    };
    callSaveTodoApi(request);
    setInput("");
  };

  const callSaveTodoApi = async (body) => {
    const response = await saveTodo(body);
    setTodos((prevState) => [...prevState, response.data.data]);
  };
  return (
    <>
      <form onSubmit={onSubmitHandler} className="flex pt-2">
        <input
          type="text"
          name="name"
          className="w-full px-3 py-2 mr4 text-gray-500 boarder rounded shadow"
          placeholder="할 일을 입력해주세요"
          onChange={onChangeInputHandler}
          value={input}
        />
        <input
          className="p-2 text-blue-400 border-2 border-blue-100 rounded hover:text-white hover:bg-blue-200"
          type="submit"
          value="입력"
        />
      </form>
    </>
  );
};

export default Form;
