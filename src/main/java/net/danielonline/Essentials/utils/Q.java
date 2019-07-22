package net.danielonline.Essentials.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q {
    public static String Random;

    static {
        // https://stackoverflow.com/a/31193401/10160296
        List quotes = new ArrayList();

        // populate the list
        quotes.add("\"Every man has his secret sorrows which the world knows not; and often times we call a man cold when he is only sad.\" -Henry Wadsworth Longfellow");
        quotes.add("\"I came, I saw, I conquered.\" - Julius Caesar");
        quotes.add("\"Our greatest glory is not in never falling, but in rising every time we fall.\" - Confucius");
        quotes.add("\"History will be kind for me for I intend to write it.\" - Winston Churchill");
        quotes.add("\"If you are neutral in situations of injustice, you have chosen the side of the oppressor. If an elephant has its foot on the tail of a mouse and you say that you are neutral, the mouse will not appreciate your neutrality.\" - Desmond Tutu");
        quotes.add("\"History is a relentless master. It has no present, only the past rushing into the future. To try to hold fast is to be swept aside.\" - John F. Kennedy");
        quotes.add("\"Those who do not remember the past are condemned to repeat it.\" - George Santayana"); // plan: make a random quote appear on startup
        quotes.add("\"A pint of sweat, saves a gallon of blood.\" - George S. Patton");
        quotes.add("\"This is one small step for a man, one giant leap for mankind.\" - Neil Armstrong");
        quotes.add("\"History is the version of past events that people have decided to agree upon.\" - Napoleon Bonaparte");
        quotes.add("\"To see the world, things dangerous to come to, to see behind walls, to draw closer, to find each other and to feel. That is the purpose of life.\" - Life Motto Secret Life of Walter Mitty");
        quotes.add("\"Beautiful things don't seek attention\" - Sean O'Connell in The Secret Life of Walter Mitty");
        quotes.add("\"The only thing we have to fear is fear itself\" - Franklin D. Roosevelt");
        quotes.add("\"Even if I knew that tomorrow the world would go to pieces, I would still plant my apple tree.\" - Martin Luther");
        quotes.add("\"Thousands of candles can be lighted from a single candle, and the life of the candle will not be shortened. Happiness never decreases by being shared.\" - Buddha");
        quotes.add("\"We can't help everyone, but everyone and help someone.\" - Ronald Reagan");
        quotes.add("\"Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.\" - Thomas A. Edison");
        quotes.add("\"Even if you fall on your face, you're still moving forward.\" - Victor Kiam");
        quotes.add("\"Strive not to be a success, but rather to be of value.\" - Albert Einstein");
        quotes.add("\"You miss 100% of the shots you don't take.\" - Wayne Gretzky");
        quotes.add("\"Your time is limited, so don't waste it living someone else's life.\" - Steve Jobs");
        quotes.add("\"The only person you are destined to become is the person you decide to be.\" - Ralph Waldo Emerson");
        quotes.add("\"Fall seven times and stand up eight\" - Japanese Proverb");
        quotes.add("\"Everything has beauty, but not everyone can see.\" - Confucius");
        quotes.add("\"A person who never made a mistake never tried anything new.\" - Albert Einstein");
        quotes.add("\"The person who says it cannot be done should not interrupt the person who is doing it.\" - Chinese Proverb");
        quotes.add("\"It does not matter how slowly you go as long as you do not stop.\" - Confucius");
        quotes.add("\"Everything you see exists together in a delicate balance. As king, you need to understand that balance and respect all the creatures, from the crawling ant to the leaping antelope. \" - Mufasa to Simba in The Lion King");
        quotes.add("\"Hakuna Matata - It means no worries for the rest of your days.\" - The Lion King");
        quotes.add("\"You must take your place in the Circle of Life.\" - Mufasa to Simba in The Lion King");
        quotes.add("\"The journey of a thousand miles begins with one step.\" - Lao Tzu");
        quotes.add("\"I'll be with you... even if you can't see me.\" - Little Foot's Mother in Land Before Tim");
        quotes.add("\"You were worth it, old friend, and a thousand times over.\" - Where the red fern grows.");
        quotes.add("\"GET IN THE FUCKING CAR, MORTY!\" - Rick in Bushworld Adventures");
        // shuffle the list
        Collections.shuffle(quotes);
        //System.out.println("Collection after shuffle: "+ quotes);

        Q.Random = quotes.get(0).toString();
    }
}
