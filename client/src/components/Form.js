import { useState } from "react";

const Form = (props) => {
  const { setTodos } = props;

  const [input, setInput] = useState("");
  const onChangeInputHandler = (e) => {
    setInput(e.target.value);
  };
  const onSubmitHandler = (e) => {
    e.preventDefault();
    const newInput = {
      name: input,
      isChecked: false,
      id: 5
    };
    setTodos((prevState) => [...prevState, newInput]);
    setInput("");
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
