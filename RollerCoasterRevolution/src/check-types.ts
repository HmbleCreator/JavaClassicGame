
import Matter from 'matter-js';
import { Howl } from 'howler';

const engine = Matter.Engine.create();
const sound = new Howl({ src: ['sound.mp3'] });

console.log(engine, sound);
